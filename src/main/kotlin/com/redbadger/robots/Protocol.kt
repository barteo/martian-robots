package com.redbadger.robots

import java.lang.Integer.parseInt
import java.lang.NumberFormatException

class MarsSurfaceDimensionsMessage private constructor() {
    companion object {
        fun decode(message: String): MarsSurfaceDimensions {
            val dimension =  message.trim().split(" ")
            if (dimension.size != 2) {
                throw ParseException()
            }
            try {
                return MarsSurfaceDimensions(
                    southNorthSize = parseInt(dimension[0]),
                    eastWestSize = parseInt(dimension[1])
                )
            } catch (e: NumberFormatException) {
                throw ParseException()
            }
        }
    }
}

class RobotInputMessage private constructor() {

    companion object {
        fun decode(message: String): RobotInput {
            val lines = message.split("\n")
            if (lines.size != 3) {
                throw ParseException()
            }
            val items =  lines[0].split(" ")
            if (items.size != 3) {
                throw ParseException()
            }
            try {
                val initialPosition = RobotPosition(
                    x = parseInt(items[1]),
                    y = parseInt(items[0]),
                    orientation = parseOrientation(items[2])
                )
                val instructions = mutableListOf<RobotInstruction>()
                for (c in lines[1].toCharArray()) {
                    instructions.add(parseInstruction(c))
                }
                return RobotInput(
                    initialPosition = initialPosition,
                    instructions = instructions
                )
            } catch (e: NumberFormatException) {
                throw ParseException()
            } catch (e: IllegalArgumentException) {
                throw ParseException()
            }
        }

        private fun parseOrientation(input: String): Orientation {
            if (input.length != 1) {
                throw ParseException()
            }
            return when (input[0]) {
                Orientation.NORTH.orientation -> Orientation.NORTH
                Orientation.SOUTH.orientation -> Orientation.SOUTH
                Orientation.EAST.orientation -> Orientation.EAST
                Orientation.WEST.orientation -> Orientation.WEST
                else -> throw ParseException()
            }
        }

        private fun parseInstruction(input: Char): RobotInstruction {
            return when (input) {
                RobotInstruction.LEFT.instruction -> RobotInstruction.LEFT
                RobotInstruction.RIGHT.instruction -> RobotInstruction.RIGHT
                RobotInstruction.FORWARD.instruction -> RobotInstruction.FORWARD
                else -> throw ParseException()
            }
        }
    }
}

class RobotOutputMessage private constructor() {
    companion object {
        fun encode(robotOutput: RobotOutput): String {
            val position =
                "${robotOutput.finalPosition.y} " +
                "${robotOutput.finalPosition.x}"
            return when (robotOutput.status) {
                RobotStatus.LOST -> "$position ${RobotStatus.LOST.name}"
                else -> position
            }
        }
    }
}

class ParseException : Exception()
