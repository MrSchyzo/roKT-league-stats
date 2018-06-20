package com.mrschyzo.api.rlstats.providers

import com.mrschyzo.api.rlstats.models.Platform
import com.mrschyzo.api.rlstats.models.Playlist
import com.mrschyzo.api.rlstats.models.Season
import com.mrschyzo.api.rlstats.models.Tier

interface RLDataProvider {
    fun getPlatform(id: Int) : Platform?

    fun getPlaylist(id: Int) : Playlist?

    fun getTier(id: Int) : Tier?

    fun getSeason(id: Int) : Season?


    fun allPlatforms() : Collection<Platform>

    fun allPlaylists() : Collection<Playlist>

    fun allTiers() : Collection<Tier>

    fun allSeasons() : Collection<Season>


    fun searchPlatforms(partialName: String): Collection<Platform>

    fun searchPlaylists(partialName: String): Collection<Playlist>

    fun searchTiers(partialName: String): Collection<Tier>
}