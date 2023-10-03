package facts.fruit.ui

import facts.fruit.data.Fruit
import facts.fruit.data.factsFriuts

data class MainState(
    val status: ApplicationStatus = ApplicationStatus.Loading,
    val fruit: Fruit = factsFriuts.first()
)
