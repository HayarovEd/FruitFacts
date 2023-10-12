package com.facts.fruit.tuti.ui

import com.facts.fruit.tuti.data.Fruit
import com.facts.fruit.tuti.data.factsFriuts

data class MainState(
    val status: ApplicationStatus = ApplicationStatus.Mock,//Loading,
    val fruit: Fruit = factsFriuts.first()
)
