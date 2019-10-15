package engine.runtime

import math.*

object Input {

    val keyboard = mutableMapOf<Key, Boolean>()

    val mouse = mutableMapOf<Int, Boolean>()

    var cursor = Float2.zero

}

