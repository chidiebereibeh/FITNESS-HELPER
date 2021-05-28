package com.miguelrochefort.fitnesscamera

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.TextToSpeech.QUEUE_ADD
import com.miguelrochefort.fitnesscamera.lib.BodyPart
import com.miguelrochefort.fitnesscamera.lib.Person


class RepetitionCounter(context: Context) {


    val MIN_AMPLITUDE = 40
    val REP_THRESHOLD = 0.8
    val MIN_CONFIDENCE = 0.5

    var count =3

    var first = true
    var goal = 1
    var prev_y = 0
    var prev_dy = 0
    var top = 0
    var bottom = 0

    fun OnFrame(person: Person) : Int {
        //WriteToCsv(person)

        // Right wrist is the keypoint
        if (person.keyPoints[BodyPart.RIGHT_WRIST.ordinal].score >= MIN_CONFIDENCE) {
            var y = 10000 - person.keyPoints[BodyPart.RIGHT_WRIST.ordinal].position.y
            var dy = y - prev_y
            if (!first) {
                if (bottom != 1000000 && top != 0) {
                    if (goal == 1 && dy > 0  * REP_THRESHOLD) {
                        if (top - bottom > MIN_AMPLITUDE) {
                            count++
                            goal = -1

                        }
                    }
                    else if (goal == -1 && dy < 0 && (top - y) > (top - bottom) * REP_THRESHOLD) {
                        goal = 1
                    }
                }

                // TODO: Use MIN_AMPLITUDE
                if (dy < 0 && prev_dy >= 0 && prev_y - bottom > MIN_AMPLITUDE) {
                    top = prev_y
                }
                else if (dy > 0 && prev_dy <= 0 && top - prev_y > MIN_AMPLITUDE) {
                    bottom = prev_y
                }
            }

            first = false
            prev_y = y
            prev_dy = dy
        }

        return count
    }

    // Write frames to CSV for later analysis in Google Colab
    var csv = ""
    fun WriteToCsv(person: Person) {
        val x = System.currentTimeMillis()
        val ys = person.keyPoints.map { kp -> if (kp.score >= 0.5) kp.position.y.toString() else "" }
        val values = ys.joinToString(",")
        csv += "${x},${values}\n"
    }



    fun Reset() {
        count = 0
    }
}