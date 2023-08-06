package app.kit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val defaultScope = CoroutineScope(Dispatchers.Default)

lateinit var rememberScope : CoroutineScope