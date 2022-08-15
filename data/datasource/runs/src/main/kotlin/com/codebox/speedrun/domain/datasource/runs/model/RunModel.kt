package com.codebox.speedrun.domain.datasource.runs.model

data class RunModel(
    val data: List<Data>,
    val pagination: Pagination
) {
    data class Data(
        val id: String,
        val weblink: String,
        val game: Game,
        val level: String?,
        val category: String,
        val videos: Videos,
        val comment: String?,
        val status: Status,
        val players: List<Player>,
        val date: String,
        val submitted: String,
        val times: Times,
        val system: System,
        val splits: Any?,
        val values: Map<String, String>?,
        val links: List<Link>
    ) {
        data class Game(
            val data: Data
        ) {
            data class Data(
                val id: String,
                val names: Names,
                val boostReceived: Int,
                val boostDistinctDonors: Int,
                val abbreviation: String,
                val weblink: String,
                val discord: String,
                val released: Int,
                val releaseDate: String,
                val ruleset: Ruleset,
                val romhack: Boolean,
                val gametypes: List<String>,
                val platforms: List<String>,
                val regions: List<String>,
                val genres: List<String>,
                val engines: List<String>,
                val developers: List<String>,
                val publishers: List<String>,
                val moderators: Map<String, String>,
                val created: String?,
                val assets: Assets,
                val links: List<Link>
            ) {
                data class Names(
                    val international: String,
                    val japanese: String?,
                    val twitch: String
                )

                data class Ruleset(
                    val showMilliseconds: Boolean,
                    val requireVerification: Boolean,
                    val requireVideo: Boolean,
                    val runTimes: List<String>,
                    val defaultTime: String,
                    val emulatorsAllowed: Boolean
                )

                data class Assets(
                    val logo: Logo,
                    val coverTiny: CoverTiny,
                    val coverSmall: CoverSmall,
                    val coverMedium: CoverMedium,
                    val coverLarge: CoverLarge,
                    val icon: Icon,
                    val trophy1st: Trophy1st,
                    val trophy2nd: Trophy2nd,
                    val trophy3rd: Trophy3rd,
                    val trophy4th: Trophy4th,
                    val background: Background,
                    val foreground: Foreground
                ) {
                    data class Logo(
                        val uri: String
                    )

                    data class CoverTiny(
                        val uri: String
                    )

                    data class CoverSmall(
                        val uri: String
                    )

                    data class CoverMedium(
                        val uri: String
                    )

                    data class CoverLarge(
                        val uri: String
                    )

                    data class Icon(
                        val uri: String
                    )

                    data class Trophy1st(
                        val uri: String
                    )

                    data class Trophy2nd(
                        val uri: String
                    )

                    data class Trophy3rd(
                        val uri: String
                    )

                    data class Trophy4th(
                        val uri: String?
                    )

                    data class Background(
                        val uri: String?
                    )

                    data class Foreground(
                        val uri: Any?
                    )
                }

                data class Link(
                    val rel: String,
                    val uri: String
                )
            }
        }

        data class Videos(
            val links: List<Link>
        ) {
            data class Link(
                val uri: String
            )
        }

        data class Status(
            val status: String,
            val examiner: String,
            val verifyDate: String
        )

        data class Player(
            val rel: String,
            val id: String,
            val uri: String
        )

        data class Times(
            val primary: String,
            val primary_t: Double,
            val realtime: String?,
            val realtime_t: Double,
            val realtime_noloads: Any?,
            val realtime_noloads_t: Int,
            val ingame: String?,
            val ingamet: Double
        )

        data class System(
            val platform: String,
            val emulated: Boolean,
            val region: String?
        )

        data class Link(
            val rel: String,
            val uri: String
        )
    }

    data class Pagination(
        val offset: Int,
        val max: Int,
        val size: Int,
        val links: List<Link>
    ) {
        data class Link(
            val rel: String,
            val uri: String
        )
    }
}