package com.redbadger.robots

enum class Orientation(val orientation: Char) {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W')
}

data class RobotPosition(
    val x: Int,
    val y: Int,
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
    val status: RobotStatus = RobotStatus.RESPONDING
)

data class MarsSurfaceDimension(
    val eastWestSize: Int,
    val southNorthSize: Int
)
