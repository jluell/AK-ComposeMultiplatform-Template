package shared.data.mapper

import BowlingDatabase.Coffin_eliminations
import BowlingDatabase.Coffin_games
import BowlingDatabase.Coffin_turns
import BowlingDatabase.Dog_sitters
import BowlingDatabase.Events
import BowlingDatabase.Forty_shot_scores
import BowlingDatabase.Penalties
import BowlingDatabase.Player_rankings
import BowlingDatabase.Players
import BowlingDatabase.Rounds
import BowlingDatabase.Yearly_summaries
import kotlinx.datetime.LocalDate
import kotlin.time.Instant
import kotlin.time.ExperimentalTime
import kotlinx.serialization.json.Json
import shared.domain.model.CoffinElimination
import shared.domain.model.CoffinGame
import shared.domain.model.CoffinTurn
import shared.domain.model.DogSitter
import shared.domain.model.Event
import shared.domain.model.EventStatus
import shared.domain.model.FortyShotScore
import shared.domain.model.Lane
import shared.domain.model.Penalty
import shared.domain.model.PenaltyAmount
import shared.domain.model.PenaltyType
import shared.domain.model.Player
import shared.domain.model.PlayerRanking
import shared.domain.model.RoundScore
import shared.domain.model.YearlySummary

/**
 * Mapper functions to convert between domain models and database entities.
 */
// Player mappers
@OptIn(ExperimentalTime::class)
fun Player.toEntity(): Players = Players(
    id = id,
    name = name,
    join_date = joinDate.toString(),
    is_active = if (isActive) 1L else 0L,
    created_at = createdAt.toString()
)

@OptIn(ExperimentalTime::class)
fun Players.toDomain(): Player = Player(
    id = id,
    name = name,
    joinDate = LocalDate.parse(join_date),
    isActive = is_active == 1L,
    createdAt = Instant.parse(created_at)
)

// Event mappers
@OptIn(ExperimentalTime::class)
fun Event.toEntity(): Events = Events(
    id = id,
    date = date.toString(),
    location = location,
    status = status.name,
    created_at = createdAt.toString()
)

@OptIn(ExperimentalTime::class)
fun Events.toDomain(): Event = Event(
    id = id,
    date = LocalDate.parse(date),
    location = location,
    status = EventStatus.valueOf(status),
    createdAt = Instant.parse(created_at)
)

// RoundScore mappers
fun RoundScore.toEntity(scoreId: String, roundId: String): Rounds = Rounds(
    id = roundId,
    score_id = scoreId,
    round_number = roundNumber.toLong(),
    lane = lane.name,
    throw_1 = throws[0].toLong(),
    throw_2 = throws[1].toLong(),
    throw_3 = throws[2].toLong(),
    throw_4 = throws[3].toLong(),
    throw_5 = throws[4].toLong(),
    throw_6 = throws[5].toLong(),
    throw_7 = throws[6].toLong(),
    throw_8 = throws[7].toLong(),
    throw_9 = throws[8].toLong(),
    throw_10 = throws[9].toLong(),
    total = total.toLong()
)

fun Rounds.toDomain(): RoundScore = RoundScore(
    roundNumber = round_number.toInt(),
    lane = Lane.valueOf(lane),
    throws = listOf(
        throw_1.toInt(), throw_2.toInt(), throw_3.toInt(), throw_4.toInt(), throw_5.toInt(),
        throw_6.toInt(), throw_7.toInt(), throw_8.toInt(), throw_9.toInt(), throw_10.toInt()
    ),
    total = total.toInt()
)

// FortyShotScore mappers
@OptIn(ExperimentalTime::class)
fun FortyShotScore.toEntity(): Forty_shot_scores = Forty_shot_scores(
    id = id,
    event_id = eventId,
    player_id = playerId,
    total_score = totalScore.toLong(),
    nines_hit = ninesHit.toLong(),
    created_at = createdAt.toString()
)

@OptIn(ExperimentalTime::class)
fun Forty_shot_scores.toDomain(rounds: List<RoundScore>): FortyShotScore = FortyShotScore(
    id = id,
    eventId = event_id,
    playerId = player_id,
    rounds = rounds,
    totalScore = total_score.toInt(),
    ninesHit = nines_hit.toInt(),
    createdAt = Instant.parse(created_at)
)

// Penalty mappers
@OptIn(ExperimentalTime::class)
fun Penalty.toEntity(): Penalties = Penalties(
    id = id,
    event_id = eventId,
    player_id = playerId,
    type = type.name,
    amount = amount.value,
    description = description,
    created_at = createdAt.toString()
)

@OptIn(ExperimentalTime::class)
fun Penalties.toDomain(): Penalty = Penalty(
    id = id,
    eventId = event_id,
    playerId = player_id,
    type = PenaltyType.valueOf(type),
    amount = PenaltyAmount(amount),
    description = description,
    createdAt = Instant.parse(created_at)
)

// CoffinGame mappers
@OptIn(ExperimentalTime::class)
fun CoffinGame.toEntity(): Coffin_games = Coffin_games(
    id = id,
    event_id = eventId,
    player_order = Json.encodeToString(playerOrder),
    created_at = createdAt.toString()
)

@OptIn(ExperimentalTime::class)
fun Coffin_games.toDomain(
    turns: List<CoffinTurn>,
    eliminations: List<CoffinElimination>
): CoffinGame = CoffinGame(
    id = id,
    eventId = event_id,
    playerOrder = Json.decodeFromString(player_order),
    turns = turns,
    eliminations = eliminations,
    createdAt = Instant.parse(created_at)
)

// CoffinTurn mappers
fun CoffinTurn.toEntity(gameId: String, turnId: String): Coffin_turns = Coffin_turns(
    id = turnId,
    game_id = gameId,
    turn_number = turnNumber.toLong(),
    player_id = playerId,
    score = score.toLong(),
    boards_received = boardsReceived.toLong()
)

fun Coffin_turns.toDomain(): CoffinTurn = CoffinTurn(
    turnNumber = turn_number.toInt(),
    playerId = player_id,
    score = score.toInt(),
    boardsReceived = boards_received.toInt()
)

// CoffinElimination mappers
fun CoffinElimination.toEntity(gameId: String, eliminationId: String): Coffin_eliminations = Coffin_eliminations(
    id = eliminationId,
    game_id = gameId,
    player_id = playerId,
    elimination_turn = eliminationTurn.toLong(),
    final_rank = finalRank.toLong()
)

fun Coffin_eliminations.toDomain(): CoffinElimination = CoffinElimination(
    playerId = player_id,
    eliminationTurn = elimination_turn.toInt(),
    finalRank = final_rank.toInt()
)

// DogSitter mappers
fun DogSitter.toEntity(): Dog_sitters = Dog_sitters(
    id = id,
    event_id = eventId,
    player_id = playerId,
    assigned_date = assignedDate.toString(),
    is_current = if (isCurrent) 1L else 0L
)

fun Dog_sitters.toDomain(): DogSitter = DogSitter(
    id = id,
    eventId = event_id,
    playerId = player_id,
    assignedDate = LocalDate.parse(assigned_date),
    isCurrent = is_current == 1L
)

// YearlySummary mappers
@OptIn(ExperimentalTime::class)
fun YearlySummary.toEntity(): Yearly_summaries = Yearly_summaries(
    id = id,
    year = year.toLong(),
    overall_winner = overallWinner,
    wet_dog_winner = wetDogWinner,
    calculated_at = calculatedAt.toString()
)

@OptIn(ExperimentalTime::class)
fun Yearly_summaries.toDomain(rankings: List<PlayerRanking>): YearlySummary = YearlySummary(
    id = id,
    year = year.toInt(),
    playerRankings = rankings,
    overallWinner = overall_winner,
    wetDogWinner = wet_dog_winner,
    calculatedAt = Instant.parse(calculated_at)
)

// PlayerRanking mappers
fun PlayerRanking.toEntity(summaryId: String, rankingId: String): Player_rankings = Player_rankings(
    id = rankingId,
    summary_id = summaryId,
    player_id = playerId,
    best_n_scores = Json.encodeToString(bestNScores),
    best_n_total = bestNTotal.toLong(),
    rank = rank.toLong()
)

fun Player_rankings.toDomain(): PlayerRanking = PlayerRanking(
    playerId = player_id,
    bestNScores = Json.decodeFromString(best_n_scores),
    bestNTotal = best_n_total.toInt(),
    rank = rank.toInt()
)

