package com.facts.fruit.tuti.domain

interface Service {
    fun vpnActive(): Boolean
    fun batteryLevel(): Int
    fun checkIsEmu(): Boolean
}