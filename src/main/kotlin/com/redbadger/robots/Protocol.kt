package com.redbadger.robots

class MarsSurfaceDimensionMessage private constructor() {
    companion object {
        fun decode(message: String): MarsSurfaceDimension {
            return null
        }
    }
}

class RobotInputMessage private constructor() {

    companion object {
        fun decode(message: String): RobotInput {
            return null
        }
    }
}

class RobotOutputMessage private constructor() {
    companion object {
        fun encode(robotOutput: RobotOutput): String {
            return null
        }
    }
}

class ParseException : Exception()
