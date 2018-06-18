package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

enum class Stat(val value: String){
    @JsonProperty("wins")
    WINS("wins"),

    @JsonProperty("goals")
    GOALS("goals"),

    @JsonProperty("mvps")
    MVPS("mvps"),

    @JsonProperty("saves")
    SAVES("saves"),

    @JsonProperty("shots")
    SHOTS("shots"),

    @JsonProperty("assists")
    ASSISTS("assists")
}