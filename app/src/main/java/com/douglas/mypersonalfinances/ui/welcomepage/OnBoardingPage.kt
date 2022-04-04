package com.douglas.mypersonalfinances.ui.welcomepage

import androidx.annotation.DrawableRes
import com.douglas.mypersonalfinances.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.ic_home,
        title = "Bienvenido!!",
        description = "Gracias por instalar la applicacion, en ella" +
                " te voy a ayudar con tus finanzas personales."
    )

    object Second : OnBoardingPage(
        image = R.drawable.ic_add,
        title = "Anota tus transacciones",
        description = "Puedes anotar tanto tus gastos en efectivo" +
                " o los pagos con tarjeta."
    )

    object Third : OnBoardingPage(
        image = R.drawable.ic_dashboard,
        title = "Visualiza tus gastos e ingresos",
        description = "Podras visualizar y gestionar tus finanzas " +
                "que tanto deseas para pegarte tus caprichos."
    )
}