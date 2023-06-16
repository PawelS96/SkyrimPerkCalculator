@file:Suppress("SameParameterValue")

package com.pawels96.skyrimperkcalculator

import org.junit.Test
import java.util.*

class SkillGenerator {

    private val text = """
    """

    private val parser: PerkParser = AdamantParser()
    private val perks = parser.parse(text.lines())

    private val skillName = "alchemy"
    private val skillPrefix: String? = null
    private val prefix = "ada"

    @Test
    fun generate() {
        val enum = generateEnum(skillName, skillPrefix, prefix, perks)
        val names = generatePerkNamesString(skillName, skillPrefix, prefix, perks)
        val descriptions = generatePerkDescriptionsString(skillName, skillPrefix, prefix, perks)

        println()
        print(enum)
        println()
        println()
        print(names)
        println()
        println()
        print(descriptions)
        println()
        println()
    }
}

private interface PerkParser {
    fun parse(lines: List<String>): List<GeneratorPerk>
}

private class AdamantParser : PerkParser {
    override fun parse(lines: List<String>): List<GeneratorPerk> {
        return lines
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { line ->
                GeneratorPerk(
                    name = line.substringBefore("(").trim(),
                    skillLevels = line
                        .substringAfter("(")
                        .substringBefore(")")
                        .split("/")
                        .map { it.toInt() },
                    description = line.substringAfter(": ").trim()
                )
            }
    }
}

private fun generateEnum(
    skillName: String,
    skillPrefix: String?,
    prefix: String,
    perks: List<GeneratorPerk>
): String {
    return buildString {
        append("import com.pawels96.skyrimperkcalculator.domain.IPerk")
        appendLine()
        append("import com.pawels96.skyrimperkcalculator.domain.PerkInfo")
        appendLine()
        appendLine()
        append(
            "enum class ${
                prefix.capitalize().clean()
            }_${skillName.capitalize()}(x: Float, y: Float, vararg skill: Int) : IPerk {"
        )
        appendLine()
        appendLine()
        val enumPerks = perks.map { perk ->
            val name = listOf(
                prefix.uppercase(),
                (skillPrefix ?: skillName.take(3)).uppercase(),
                perk.name.toUpper()
            ).joinToString("_")
            val params = "(0.0f, 0.0f, ${perk.skillLevels.joinToString(", ") { it.toString() }})"
            name + params
        }
        val longestName = enumPerks.map { it.substringBefore("(") }.maxBy { it.length }
        val formattedEnumPerks = enumPerks.map {
            val name = it.substringBefore("(")
            val spacesToAdd = longestName.length - name.length
            it.replace("(", " ".repeat(spacesToAdd) + "(")
        }
        append(formattedEnumPerks.joinToString(",\n") + ";")
        appendLine()
        appendLine()
        append("override val perkInfo: PerkInfo = PerkInfo(skill, x, y)")
        appendLine()
        append("}")
    }
}

private fun generatePerkNamesString(
    skillName: String,
    skillPrefix: String?,
    prefix: String,
    perks: List<GeneratorPerk>
): String {
    return perks.joinToString("\n") {
        val key = listOf("p", prefix, skillPrefix ?: skillName.take(3), it.name.toLower())
            .joinToString("_")
            .lowercase()
        toXml(key, it.name.escape())
    }
}

private fun generatePerkDescriptionsString(
    skillName: String,
    skillPrefix: String?,
    prefix: String,
    perks: List<GeneratorPerk>
): String {
    return perks.joinToString("\n") {
        val key = listOf("d", prefix, skillPrefix ?: skillName.take(3), it.name.toLower())
            .joinToString("_")
            .lowercase()
        toXml(key, it.description.escape())
    }
}

private fun String.toUpper(): String {
    return uppercase(Locale.getDefault()).clean()
}

private fun String.toLower(): String {
    return lowercase(Locale.getDefault()).clean()
}

private fun String.clean(): String {
    return this
        .replace(" ", "_")
        .replace("-", "_")
        .replace("'", "")
        .replace("’", "")
}

private fun String.escape(): String {
    return replace("'", "\\'")
}

private fun toXml(name: String, value: String): String {
    val xml = "<string name=\"$name\">$value"
    return xml.replace("null ", "") + "</string>"
}

private data class GeneratorPerk(
    val name: String,
    val skillLevels: List<Int>,
    val description: String
)
