package com.puzzlebench.clean_marvel_kotlin.presentation.base


abstract class Presenter<out V>(open val view: V)
