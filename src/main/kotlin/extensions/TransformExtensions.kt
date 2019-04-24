package extensions

import engine.world.Transform

val Transform.children get() = childrenSet.asSequence()