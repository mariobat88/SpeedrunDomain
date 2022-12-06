package com.speedrun.domain.kit.run.ui

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.speedrun.domain.repo.runs.model.RunModel

@Composable
fun System(
    system: RunModel.System?,
    color: Color,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign,
    style: TextStyle = LocalTextStyle.current
) {
    val superscript = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 10.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Text(
        text = buildAnnotatedString {
            append(system?.platform?.name ?: "")
            if (system?.emulated == true) {
                withStyle(superscript) {
                    append(" EMU")
                }
            }

        },
        color = color,
        fontSize = fontSize,
        textAlign = textAlign,
        style = style
    )
}