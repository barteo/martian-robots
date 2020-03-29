package com.redbadger.robots

enum class Orientation(val orientation: Char) {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W')
}

data class RobotPosition(
    val x: Int, // eastWest
    val y: Int, // southNorth
    val orientation: Orientation
)

enum class RobotInstruction(val instruction: Char) {
    LEFT('L'),
    RIGHT('R'),
    FORWARD('F'),
    // TODO extend to multiple additional types, likely not in enum
    OTHERS('O')
}

data class RobotInput(
    val initialPosition: RobotPosition,
    val instructions: List<RobotInstruction>
)

enum class RobotStatus(val status: String) {
    LOST("LOST"),
    RESPONDING("")
}

data class RobotOutput(
    val finalPosition: RobotPosition,
    val status: RobotStatus
)

data class MarsSurfaceDimension(
    val southNorthSize: Int,
    val eastWestSize: Int
)
