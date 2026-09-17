"""Renders the mod's block items as 1024x1024 isometric icons that match the hosted vanilla renders.

Reads the datagen block models, so every block the mod registers gets an icon. Flat item textures
(spawn eggs) are scaled up with nearest-neighbour. Output goes to the folder given as the first argument; upload it with
the mod-texture-uploader skill to gs://coolerpromc/textures/moresponge/.

    py docs/scripts/render_icons.py <out_dir>
"""
import json
import sys
from pathlib import Path

from PIL import Image

ROOT = Path(__file__).resolve().parents[2]
ASSETS = ROOT / 'common/src/main/resources/assets/moresponge'
MODELS = ROOT / 'common/src/generated/resources/assets/moresponge/models'

W = 1024
# Corners of the vanilla renders: top, left and right of the top face, its centre, and the height of a side.
TOP, LEFT, RIGHT, CENTER, H = (512, 9), (59, 235), (965, 235), (512, 461), 554
# Face brightness measured from the vanilla renders.
SHADE_TOP, SHADE_LEFT, SHADE_RIGHT = 1.0, 0.65, 0.4


def texture(ref):
    namespace, path = ref.split(':')
    img = Image.open(ASSETS / 'textures' / f'{path}.png').convert('RGBA')
    return img.crop((0, 0, img.width, img.width))  # first frame of an animated texture


def layered(*refs):
    img = texture(refs[0])
    for ref in refs[1:]:
        img = Image.alpha_composite(img, texture(ref).resize(img.size, Image.NEAREST))
    return img


def shade(img, factor):
    r, g, b, a = img.split()
    lut = [min(255, round(i * factor)) for i in range(256)]
    return Image.merge('RGBA', (r.point(lut), g.point(lut), b.point(lut), a))


def face(tex, origin, eu, ev):
    """Maps the texture (u right, v down) onto the parallelogram origin + u*eu + v*ev."""
    big = tex.resize((tex.width * 32, tex.width * 32), Image.NEAREST)
    s = big.width
    a, b, c, d = eu[0] / s, ev[0] / s, eu[1] / s, ev[1] / s
    det = a * d - b * c
    ia, ib, ic, id_ = d / det, -b / det, -c / det, a / det
    ox, oy = origin
    coeffs = (ia, ib, -(ia * ox + ib * oy), ic, id_, -(ic * ox + id_ * oy))
    return big.transform((W, W), Image.AFFINE, coeffs, resample=Image.NEAREST)


def render(top, left, right):
    out = Image.new('RGBA', (W, W))
    out.alpha_composite(face(shade(top.transpose(Image.ROTATE_180), SHADE_TOP), LEFT,
                             (TOP[0] - LEFT[0], TOP[1] - LEFT[1]), (CENTER[0] - LEFT[0], CENTER[1] - LEFT[1])))
    out.alpha_composite(face(shade(left, SHADE_LEFT), LEFT, (CENTER[0] - LEFT[0], CENTER[1] - LEFT[1]), (0, H)))
    out.alpha_composite(face(shade(right, SHADE_RIGHT), CENTER, (RIGHT[0] - CENTER[0], RIGHT[1] - CENTER[1]), (0, H)))
    return out


def block_faces(model):
    textures = model['textures']
    parent = model.get('parent')
    if parent == 'minecraft:block/cube_all':
        tex = texture(textures['all'])
        return tex, tex, tex
    if parent == 'minecraft:block/orientable':
        return texture(textures['top']), texture(textures['side']), texture(textures['front'])
    if 'layer0' in textures:  # compressed sponges: base texture with the compression overlay on top
        tex = layered(*(textures[k] for k in sorted(textures) if k.startswith('layer')))
        return tex, tex, tex
    raise ValueError(f'unsupported model parent {parent}')


def main():
    out = Path(sys.argv[1])
    out.mkdir(parents=True, exist_ok=True)
    count = 0
    for item in sorted((ROOT / 'common/src/generated/resources/assets/moresponge/items').glob('*.json')):
        name = item.stem
        ref = json.loads(item.read_text())['model']['model']
        namespace, path = ref.split(':')
        if path.startswith('block/'):
            model = json.loads((MODELS / f'{path}.json').read_text())
            render(*block_faces(model)).save(out / f'{name}.png')
        else:
            texture(ref).resize((W, W), Image.NEAREST).save(out / f'{name}.png')
        count += 1
    print(f'Rendered {count} icons to {out}')


if __name__ == '__main__':
    main()
