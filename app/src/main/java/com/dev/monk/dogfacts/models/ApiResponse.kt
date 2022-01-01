package com.dev.monk.dogfacts.models

import com.google.gson.annotations.SerializedName

class ApiResponse(
    @SerializedName("facts") val facts: List<String>
)
