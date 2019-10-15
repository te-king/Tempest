package engine.gui

import wrappers.nanovg.Surface

class Dock : Frame() {

    var preferredWidth = 128
    var preferredHeight = 128

    var direction = DockDirection.Center

    override fun draw(surface: Surface) =
        when (direction) {
            DockDirection.Left -> {
                val x = 0
                val y = 0
                val width = preferredWidth
                val height = surface.height
                surface.push(x, y, width, height)
                child?.draw(surface)
                surface.pop()
            }
            DockDirection.Right -> {
                val x = surface.width - preferredWidth
                val y = 0
                val width = preferredWidth
                val height = surface.height
                surface.push(x, y, width, height)
                child?.draw(surface)
                surface.pop()
            }
            DockDirection.Top -> {
                val x = 0
                val y = 0
                val width = surface.width
                val height = preferredHeight
                surface.push(x, y, width, height)
                child?.draw(surface)
                surface.pop()
            }
            DockDirection.Bottom -> {
                val x = 0
                val y = surface.height - preferredHeight
                val width = surface.width
                val height = preferredHeight
                surface.push(x, y, width, height)
                child?.draw(surface)
                surface.pop()
            }
            DockDirection.Center -> {
                val x = (surface.width / 2) - (preferredWidth / 2)
                val y = (surface.height / 2) - (preferredHeight / 2)
                val width = preferredWidth
                val height = preferredHeight
                surface.push(x, y, width, height)
                child?.draw(surface)
                surface.pop()
            }
        }

}

enum class DockDirection {
    Left,
    Right,
    Top,
    Bottom,
    Center
}