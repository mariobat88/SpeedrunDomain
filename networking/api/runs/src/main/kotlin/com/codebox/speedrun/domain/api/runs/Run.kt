package com.codebox.speedrun.domain.api.runs


//@JsonClass(generateAdapter = true)
//data class Run(
//    @Json(name = "data") val `data`: List<Data>,
//    @Json(name = "pagination") val pagination: Pagination
//) {
//    @JsonClass(generateAdapter = true)
//    data class Data(
//        @Json(name = "id") val id: String,
//        @Json(name = "weblink") val weblink: String,
//        @Json(name = "game") val game: Game,
//        @Json(name = "level") val level: String?,
//        @Json(name = "category") val category: String,
//        @Json(name = "videos") val videos: Videos,
//        @Json(name = "comment") val comment: String?,
//        @Json(name = "status") val status: Status,
//        @Json(name = "players") val players: List<Player>,
//        @Json(name = "date") val date: String,
//        @Json(name = "submitted") val submitted: String,
//        @Json(name = "times") val times: Times,
//        @Json(name = "system") val system: System,
//        @Json(name = "splits") val splits: Any?,
//        @Json(name = "values") val values: Values?,
//        @Json(name = "links") val links: List<Link>
//    ) {
//        @JsonClass(generateAdapter = true)
//        data class Game(
//            @Json(name = "data") val `data`: Data
//        ) {
//            @JsonClass(generateAdapter = true)
//            data class Data(
//                @Json(name = "id") val id: String,
//                @Json(name = "names") val names: Names,
//                @Json(name = "boostReceived") val boostReceived: Int,
//                @Json(name = "boostDistinctDonors") val boostDistinctDonors: Int,
//                @Json(name = "abbreviation") val abbreviation: String,
//                @Json(name = "weblink") val weblink: String,
//                @Json(name = "discord") val discord: String,
//                @Json(name = "released") val released: Int,
//                @Json(name = "release-date") val releaseDate: String,
//                @Json(name = "ruleset") val ruleset: Ruleset,
//                @Json(name = "romhack") val romhack: Boolean,
//                @Json(name = "gametypes") val gametypes: List<String>,
//                @Json(name = "platforms") val platforms: List<String>,
//                @Json(name = "regions") val regions: List<String>,
//                @Json(name = "genres") val genres: List<String>,
//                @Json(name = "engines") val engines: List<String>,
//                @Json(name = "developers") val developers: List<String>,
//                @Json(name = "publishers") val publishers: List<String>,
//                @Json(name = "moderators") val moderators: Moderators,
//                @Json(name = "created") val created: String,
//                @Json(name = "assets") val assets: Assets,
//                @Json(name = "links") val links: List<Link>
//            ) {
//                @JsonClass(generateAdapter = true)
//                data class Names(
//                    @Json(name = "international") val international: String,
//                    @Json(name = "japanese") val japanese: Any?,
//                    @Json(name = "twitch") val twitch: String
//                )
//
//                @JsonClass(generateAdapter = true)
//                data class Ruleset(
//                    @Json(name = "show-milliseconds") val showMilliseconds: Boolean,
//                    @Json(name = "require-verification") val requireVerification: Boolean,
//                    @Json(name = "require-video") val requireVideo: Boolean,
//                    @Json(name = "run-times") val runTimes: List<String>,
//                    @Json(name = "default-time") val defaultTime: String,
//                    @Json(name = "emulators-allowed") val emulatorsAllowed: Boolean
//                )
//
//                @JsonClass(generateAdapter = true)
//                data class Moderators(
//                    @Json(name = "zx76gz6j") val zx76gz6j: String?,
//                    @Json(name = "0jmvy6nj") val jmvy6nj: String?,
//                    @Json(name = "zx7vdyrx") val zx7vdyrx: String?,
//                    @Json(name = "pj0rq4jw") val pj0rq4jw: String?,
//                    @Json(name = "qxkr5980") val qxkr5980: String?,
//                    @Json(name = "pj0gqv4x") val pj0gqv4x: String?,
//                    @Json(name = "o86mkpqj") val o86mkpqj: String?,
//                    @Json(name = "v81gwnq8") val v81gwnq8: String?,
//                    @Json(name = "qxklng98") val qxklng98: String?,
//                    @Json(name = "v8lm6d2j") val v8lm6d2j: String?,
//                    @Json(name = "j42lowvx") val j42lowvx: String?,
//                    @Json(name = "kj9p77x4") val kj9p77x4: String?,
//                    @Json(name = "7j4pvrmj") val j4pvrmj: String?,
//                    @Json(name = "1xyy9oyx") val xyy9oyx: String?,
//                    @Json(name = "pj0o1g4j") val pj0o1g4j: String?,
//                    @Json(name = "kjpd5k5j") val kjpd5k5j: String?,
//                    @Json(name = "68w4znlx") val w4znlx: String?,
//                    @Json(name = "xkoyq678") val xkoyq678: String?,
//                    @Json(name = "8e913qdj") val e913qdj: String?,
//                    @Json(name = "x35q3nqj") val x35q3nqj: String?,
//                    @Json(name = "jmozloy8") val jmozloy8: String?,
//                    @Json(name = "j92vden8") val j92vden8: String?,
//                    @Json(name = "j92ygno8") val j92ygno8: String?,
//                    @Json(name = "j2yevd78") val j2yevd78: String?,
//                    @Json(name = "8dw7w55j") val dw7w55j: String?,
//                    @Json(name = "xz9vng48") val xz9vng48: String?,
//                    @Json(name = "j2ydz568") val j2ydz568: String?,
//                    @Json(name = "8l0qwkr8") val l0qwkr8: String?,
//                    @Json(name = "8wkrlkq8") val wkrlkq8: String?,
//                    @Json(name = "8ge42o7j") val ge42o7j: String?,
//                    @Json(name = "8r72pyqj") val r72pyqj: String?,
//                    @Json(name = "x3qmp56j") val x3qmp56j: String?,
//                    @Json(name = "8wl37kvj") val wl37kvj: String?,
//                    @Json(name = "j51p0268") val j51p0268: String?,
//                    @Json(name = "jn91k54x") val jn91k54x: String?,
//                    @Json(name = "j9r75evj") val j9r75evj: String?,
//                    @Json(name = "x3mkmkk8") val x3mkmkk8: String?,
//                    @Json(name = "8q39n67j") val q39n67j: String?,
//                    @Json(name = "8e6rpq7j") val e6rpq7j: String?,
//                    @Json(name = "j5v4pnq8") val j5v4pnq8: String?,
//                    @Json(name = "jpo3rvk8") val jpo3rvk8: String?,
//                    @Json(name = "xymne4mx") val xymne4mx: String?,
//                    @Json(name = "8gmy3d28") val gmy3d28: String?,
//                    @Json(name = "xk32ql78") val xk32ql78: String?,
//                    @Json(name = "j4vo1gwj") val j4vo1gwj: String?,
//                    @Json(name = "j9595nv8") val j9595nv8: String?,
//                    @Json(name = "xymgy7nx") val xymgy7nx: String?,
//                    @Json(name = "xymgz9yx") val xymgz9yx: String?,
//                    @Json(name = "j4vmno5j") val j4vmno5j: String?,
//                    @Json(name = "8r3eg3d8") val r3eg3d8: String?,
//                    @Json(name = "j0gpq54x") val j0gpq54x: String?,
//                    @Json(name = "x33g1nex") val x33g1nex: String?,
//                    @Json(name = "jn2ydm4j") val jn2ydm4j: String?,
//                    @Json(name = "x33gz5ex") val x33gz5ex: String?,
//                    @Json(name = "jm3k1ee8") val jm3k1ee8: String?,
//                    @Json(name = "8dgqy6l8") val dgqy6l8: String?
//                )
//
//                @JsonClass(generateAdapter = true)
//                data class Assets(
//                    @Json(name = "logo") val logo: Logo,
//                    @Json(name = "cover-tiny") val coverTiny: CoverTiny,
//                    @Json(name = "cover-small") val coverSmall: CoverSmall,
//                    @Json(name = "cover-medium") val coverMedium: CoverMedium,
//                    @Json(name = "cover-large") val coverLarge: CoverLarge,
//                    @Json(name = "icon") val icon: Icon,
//                    @Json(name = "trophy-1st") val trophy1st: Trophy1st,
//                    @Json(name = "trophy-2nd") val trophy2nd: Trophy2nd,
//                    @Json(name = "trophy-3rd") val trophy3rd: Trophy3rd,
//                    @Json(name = "trophy-4th") val trophy4th: Trophy4th,
//                    @Json(name = "background") val background: Background,
//                    @Json(name = "foreground") val foreground: Foreground
//                ) {
//                    @JsonClass(generateAdapter = true)
//                    data class Logo(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class CoverTiny(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class CoverSmall(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class CoverMedium(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class CoverLarge(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Icon(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Trophy1st(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Trophy2nd(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Trophy3rd(
//                        @Json(name = "uri") val uri: String
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Trophy4th(
//                        @Json(name = "uri") val uri: String?
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Background(
//                        @Json(name = "uri") val uri: String?
//                    )
//
//                    @JsonClass(generateAdapter = true)
//                    data class Foreground(
//                        @Json(name = "uri") val uri: Any?
//                    )
//                }
//
//                @JsonClass(generateAdapter = true)
//                data class Link(
//                    @Json(name = "rel") val rel: String,
//                    @Json(name = "uri") val uri: String
//                )
//            }
//        }
//
//        @JsonClass(generateAdapter = true)
//        data class Videos(
//            @Json(name = "links") val links: List<Link>
//        ) {
//            @JsonClass(generateAdapter = true)
//            data class Link(
//                @Json(name = "uri") val uri: String
//            )
//        }
//
//        @JsonClass(generateAdapter = true)
//        data class Status(
//            @Json(name = "status") val status: String,
//            @Json(name = "examiner") val examiner: String,
//            @Json(name = "verify-date") val verifyDate: String
//        )
//
//        @JsonClass(generateAdapter = true)
//        data class Player(
//            @Json(name = "rel") val rel: String,
//            @Json(name = "id") val id: String,
//            @Json(name = "uri") val uri: String
//        )
//
//        @JsonClass(generateAdapter = true)
//        data class Times(
//            @Json(name = "primary") val primary: String,
//            @Json(name = "primary_t") val primaryT: Double,
//            @Json(name = "realtime") val realtime: String?,
//            @Json(name = "realtime_t") val realtimeT: Double,
//            @Json(name = "realtime_noloads") val realtimeNoloads: Any?,
//            @Json(name = "realtime_noloads_t") val realtimeNoloadsT: Int,
//            @Json(name = "ingame") val ingame: String?,
//            @Json(name = "ingame_t") val ingameT: Double
//        )
//
//        @JsonClass(generateAdapter = true)
//        data class System(
//            @Json(name = "platform") val platform: String,
//            @Json(name = "emulated") val emulated: Boolean,
//            @Json(name = "region") val region: Any?
//        )
//
//        @JsonClass(generateAdapter = true)
//        data class Values(
//            @Json(name = "kn0mj7ol") val kn0mj7ol: String?,
//            @Json(name = "7896x458") val x458: String?,
//            @Json(name = "wl30139l") val wl30139l: String?,
//            @Json(name = "j84eeg2n") val j84eeg2n: String?
//        )
//
//        @JsonClass(generateAdapter = true)
//        data class Link(
//            @Json(name = "rel") val rel: String,
//            @Json(name = "uri") val uri: String
//        )
//    }
//
//    @JsonClass(generateAdapter = true)
//    data class Pagination(
//        @Json(name = "offset") val offset: Int,
//        @Json(name = "max") val max: Int,
//        @Json(name = "size") val size: Int,
//        @Json(name = "links") val links: List<Link>
//    ) {
//        @JsonClass(generateAdapter = true)
//        data class Link(
//            @Json(name = "rel") val rel: String,
//            @Json(name = "uri") val uri: String
//        )
//    }
//}