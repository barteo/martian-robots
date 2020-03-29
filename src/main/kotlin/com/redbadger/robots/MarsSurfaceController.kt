package com.redbadger.robots

class MarsSurface constructor(
    private val dimensions: MarsSurfaceDimensions
) {
    private val northBand = BooleanArray(dimensions.eastMaxCoordinate + 1)
    private val southBand = BooleanArray(dimensions.eastMaxCoordinate + 1)
    private val eastBand = BooleanArray(dimensions.northMaxCoordinate + 1)
    private val westBand = BooleanArray(dimensions.northMaxCoordinate + 1)

    fun moveRobot(
        robotInput: RobotInput
    ): RobotOutput {
        var position = robotInput.initialPosition
        for (instruction in robotInput.instructions) {
            try {
                position = moveRobotSingleStep(
                    initialPosition = position,
                    instruction = instruction
                )
            } catch (e: RobotOutOfSurfaceException) {
                return RobotOutput(
                    finalPosition = position,
                    status = RobotStatus.LOST
                )
            }
        }
        return RobotOutput(
            finalPosition = position,
            status = RobotStatus.RESPONDING
        )
    }

    private fun moveRobotSingleStep(
        initialPosition: RobotPosition,
        instruction: RobotInstruction
    ): RobotPosition {
        return when (instruction) {
            RobotInstruction.LEFT -> rotateRobotLeft(initialPosition)
            RobotInstruction.RIGHT -> rotateRobotRight(initialPosition)
            RobotInstruction.FORWARD -> moveRobotForward(initialPosition)
            else -> throw UnknownCommandException()
        }
    }

    private fun rotateRobotLeft(
        initialPosition: RobotPosition
    ): RobotPosition {
        return when (initialPosition.orientation) {
            Orientation.NORTH ->
                initialPosition.copy(orientation = Orientation.WEST)
            Orientation.WEST ->
                initialPosition.copy(orientation = Orientation.SOUTH)
            Orientation.SOUTH ->
                initialPosition.copy(orientation = Orientation.EAST)
            Orientation.EAST ->
                initialPosition.copy(orientation = Orientation.NORTH)
        }
    }

    private fun rotateRobotRight(
        initialPosition: RobotPosition
    ): RobotPosition {
        return when (initialPosition.orientation) {
            Orientation.NORTH ->
                initialPosition.copy(orientation = Orientation.EAST)
            Orientation.EAST ->
                initialPosition.copy(orientation = Orientation.SOUTH)
            Orientation.SOUTH ->
                initialPosition.copy(orientation = Orientation.WEST)
            Orientation.WEST ->
                initialPosition.copy(orientation = Orientation.NORTH)
        }
    }

    private fun moveRobotForward(
        initialPosition: RobotPosition
    ): RobotPosition {
        return when (initialPosition.orientation) {
            Orientation.NORTH -> {
                val newPosition = initialPosition.copy(
                    y = initialPosition.y + 1
                )
                if (newPosition.y > dimensions.northMaxCoordinate) {
                    if (northBand[initialPosition.x]) {
                        initialPosition
                    } else {
                        northBand[initialPosition.x] = true
                        throw RobotOutOfSurfaceException()
                    }
                } else {
                    newPosition
                }
            }
            Orientation.SOUTH -> {
                val newPosition = initialPosition.copy(
                    y = initialPosition.y - 1
                )
                if (newPosition.y < 0) {
                    if (southBand[initialPosition.x]) {
                        initialPosition
                    } else {
                        southBand[initialPosition.x] = true
                        throw RobotOutOfSurfaceException()
                    }
                } else {
                    newPosition
                }
            }
            Orientation.EAST -> {
                val newPosition = initialPosition.copy(
                    x = initialPosition.x + 1
                )
                if (newPosition.x > dimensions.eastMaxCoordinate) {
                    if (westBand[initialPosition.y]) {
                        initialPosition
                    } else {
                        westBand[initialPosition.y] = true
                        throw RobotOutOfSurfaceException()
                    }
                } else {
                    newPosition
                }
            }
            Orientation.WEST -> {
                val newPosition = initialPosition.copy(
                    x = initialPosition.x - 1
                )
                if (newPosition.x < 0) {
                    if (eastBand[initialPosition.y]) {
                        initialPosition
                    } else {
                        eastBand[initialPosition.y] = true
                        throw RobotOutOfSurfaceException()
                    }
                } else {
                    newPosition
                }
            }
        }
    }
}

class UnknownCommandException : Exception()

class RobotOutOfSurfaceException() : Exception()
