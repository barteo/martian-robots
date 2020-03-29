package com.redbadger.robots

import org.junit.Test
import org.junit.Assert.assertEquals

class End2EndTest {
    private val inputMarsSurfaceDimensionsMessage = "5 3\n"

    private val inputRobotsMessages = listOf(
        "1 1 E\nRFRFRFRF\n",
        "3 2 N\nFRRFLLFFRRFLL\n",
        "0 3 W\nLLFFFLFLFL\n"
    )
    private val outputRobotsMessages = listOf(
        "1 1 E\n",
        "3 3 N LOST\n",
        "2 3 S\n"
    )

    @Test
    fun sampleDataTest() {
        val marsSurface = MarsSurface(
            MarsSurfaceDimensionsMessage.decode(
                inputMarsSurfaceDimensionsMessage
            )
        )
        assertEquals(
            marsSurface,
            MarsSurfaceDimension(
                southNorthSize = 5,
                eastWestSize = 3
            )
        )

        for ((input, output) in inputRobotsMessages.zip(outputRobotsMessages)) {
            val robotOutput = marsSurface.moveRobot(
                RobotInputMessage.decode(input)
            )
            assertEquals(
                RobotOutputMessage.encode(robotOutput),
                output
            )
        }
    }
}