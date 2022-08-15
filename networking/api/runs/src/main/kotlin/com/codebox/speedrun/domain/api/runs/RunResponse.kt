package com.codebox.speedrun.domain.api.runs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RunResponse(
    @Json(name = "data") val data: List<Data>,
    @Json(name = "pagination") val pagination: Pagination
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id") val id: String,
        @Json(name = "weblink") val weblink: String,
        @Json(name = "game") val game: Game,
        @Json(name = "level") val level: String?,
        @Json(name = "category") val category: String,
        @Json(name = "videos") val videos: Videos,
        @Json(name = "comment") val comment: String?,
        @Json(name = "status") val status: Status,
        @Json(name = "players") val players: List<Player>,
        @Json(name = "date") val date: String,
        @Json(name = "submitted") val submitted: String,
        @Json(name = "times") val times: Times,
        @Json(name = "system") val system: System,
        @Json(name = "splits") val splits: Any?,
        @Json(name = "values") val values: Map<String, String>?,
        @Json(name = "links") val links: List<Link>
    ) {
        @JsonClass(generateAdapter = true)
        data class Game(
            @Json(name = "data") val data: GameData
        ) {
            @JsonClass(generateAdapter = true)
            data class GameData(
                @Json(name = "id") val id: String,
                @Json(name = "names") val names: Names,
                @Json(name = "boostReceived") val boostReceived: Int,
                @Json(name = "boostDistinctDonors") val boostDistinctDonors: Int,
                @Json(name = "abbreviation") val abbreviation: String,
                @Json(name = "weblink") val weblink: String,
                @Json(name = "discord") val discord: String,
                @Json(name = "released") val released: Int,
                @Json(name = "release-date") val releaseDate: String,
                @Json(name = "ruleset") val ruleset: Ruleset,
                @Json(name = "romhack") val romhack: Boolean,
                @Json(name = "gametypes") val gametypes: List<String>,
                @Json(name = "platforms") val platforms: List<String>,
                @Json(name = "regions") val regions: List<String>,
                @Json(name = "genres") val genres: List<String>,
                @Json(name = "engines") val engines: List<String>,
                @Json(name = "developers") val developers: List<String>,
                @Json(name = "publishers") val publishers: List<String>,
                @Json(name = "moderators") val moderators: Map<String, String>,
                @Json(name = "created") val created: String,
                @Json(name = "assets") val assets: Assets,
                @Json(name = "links") val links: List<Link>
            ) {
                @JsonClass(generateAdapter = true)
                data class Names(
                    @Json(name = "international") val international: String,
                    @Json(name = "japanese") val japanese: Any?,
                    @Json(name = "twitch") val twitch: String
                )

                @JsonClass(generateAdapter = true)
                data class Ruleset(
                    @Json(name = "show-milliseconds") val showMilliseconds: Boolean,
                    @Json(name = "require-verification") val requireVerification: Boolean,
                    @Json(name = "require-video") val requireVideo: Boolean,
                    @Json(name = "run-times") val runTimes: List<String>,
                    @Json(name = "default-time") val defaultTime: String,
                    @Json(name = "emulators-allowed") val emulatorsAllowed: Boolean
                )

                @JsonClass(generateAdapter = true)
                data class Assets(
                    @Json(name = "logo") val logo: Logo,
                    @Json(name = "cover-tiny") val coverTiny: CoverTiny,
                    @Json(name = "cover-small") val coverSmall: CoverSmall,
                    @Json(name = "cover-medium") val coverMedium: CoverMedium,
                    @Json(name = "cover-large") val coverLarge: CoverLarge,
                    @Json(name = "icon") val icon: Icon,
                    @Json(name = "trophy-1st") val trophy1st: Trophy1st,
                    @Json(name = "trophy-2nd") val trophy2nd: Trophy2nd,
                    @Json(name = "trophy-3rd") val trophy3rd: Trophy3rd,
                    @Json(name = "trophy-4th") val trophy4th: Trophy4th,
                    @Json(name = "background") val background: Background,
                    @Json(name = "foreground") val foreground: Foreground
                ) {
                    @JsonClass(generateAdapter = true)
                    data class Logo(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class CoverTiny(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class CoverSmall(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class CoverMedium(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class CoverLarge(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class Icon(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class Trophy1st(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class Trophy2nd(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class Trophy3rd(
                        @Json(name = "uri") val uri: String
                    )

                    @JsonClass(generateAdapter = true)
                    data class Trophy4th(
                        @Json(name = "uri") val uri: String?
                    )

                    @JsonClass(generateAdapter = true)
                    data class Background(
                        @Json(name = "uri") val uri: String?
                    )

                    @JsonClass(generateAdapter = true)
                    data class Foreground(
                        @Json(name = "uri") val uri: Any?
                    )
                }

                @JsonClass(generateAdapter = true)
                data class Link(
                    @Json(name = "rel") val rel: String,
                    @Json(name = "uri") val uri: String
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class Videos(
            @Json(name = "links") val links: List<Link>
        ) {
            @JsonClass(generateAdapter = true)
            data class Link(
                @Json(name = "uri") val uri: String
            )
        }

        @JsonClass(generateAdapter = true)
        data class Status(
            @Json(name = "status") val status: String,
            @Json(name = "examiner") val examiner: String,
            @Json(name = "verify-date") val verifyDate: String
        )

        @JsonClass(generateAdapter = true)
        data class Player(
            @Json(name = "rel") val rel: String,
            @Json(name = "id") val id: String,
            @Json(name = "uri") val uri: String
        )

        @JsonClass(generateAdapter = true)
        data class Times(
            @Json(name = "primary") val primary: String,
            @Json(name = "primary_t") val primaryT: Double,
            @Json(name = "realtime") val realtime: String?,
            @Json(name = "realtime_t") val realtimeT: Double,
            @Json(name = "realtime_noloads") val realtimeNoloads: Any?,
            @Json(name = "realtime_noloads_t") val realtimeNoloadsT: Int,
            @Json(name = "ingame") val ingame: String?,
            @Json(name = "ingame_t") val ingameT: Double
        )

        @JsonClass(generateAdapter = true)
        data class System(
            @Json(name = "platform") val platform: String,
            @Json(name = "emulated") val emulated: Boolean,
            @Json(name = "region") val region: Any?
        )

        @JsonClass(generateAdapter = true)
        data class Link(
            @Json(name = "rel") val rel: String,
            @Json(name = "uri") val uri: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Pagination(
        @Json(name = "offset") val offset: Int,
        @Json(name = "max") val max: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "links") val links: List<Link>
    ) {
        @JsonClass(generateAdapter = true)
        data class Link(
            @Json(name = "rel") val rel: String,
            @Json(name = "uri") val uri: String
        )
    }
}