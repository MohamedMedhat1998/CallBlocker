package com.andalus.broadcastreceiversplayground.Objects

data class NormalContact(
    var uId: Long,
    var nameVar: String,
    var numberVar: String
) : Contact(uId, nameVar, numberVar)