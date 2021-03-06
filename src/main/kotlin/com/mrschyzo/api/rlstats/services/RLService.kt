package com.mrschyzo.api.rlstats.services

import com.mrschyzo.api.rlstats.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RLService {
    @GET("v1/data/platforms")
    fun getPlatforms() : Call<Collection<Platform>>

    @GET("v1/data/seasons")
    fun getSeasons() : Call<Collection<Season>>

    @GET("v1/data/playlists")
    fun getPlaylists() : Call<Collection<Playlist>>

    @GET("v1/data/tiers")
    fun getTiers() : Call<Collection<Tier>>

    @GET("v1/player?")
    fun getPlayer(
            @Query("unique_id")
            playerId: String,
            @Query("platform_id")
            platformId: Int
    ) : Call<Player>

    @POST("v1/player/batch")
    fun getPlayers(
            @Body
            players: Collection<PlayerEntry>
    ) : Call<Collection<Player>>

    @GET("v1/search/players?")
    fun searchPlayersByDisplayName(
            @Query("display_name")
            name: String,
            @Query("page")
            pageNumber: Int = 0
    ) : Call<PlayerSearchResult>

    @GET("v1/leaderboard/ranked")
    fun getLeaderboardByRankedPlaylistId(
            @Query("playlist_id")
            playlistId: Int
    ): Call<Collection<Player>>

    @GET("v1/leaderboard/stat")
    fun getLeaderboardByStatType(
            @Query("type")
            statType: Stat
    ): Call<Collection<Player>>
}