/*
 * This file is part of fabric-loom, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2023 FabricMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.fabricmc.loom.test.unit.architectury

import spock.lang.Specification

import static dev.architectury.loom.util.MappingOption.*

class MappingOptionTest extends Specification {
	def "namespace filtering with empty array should not change mapping option"() {
		when:
		def filtered = mappingOption.forNamespaces(namespaces as String[])
		then:
		filtered == expected
		where:
		mappingOption | namespaces      | expected
		DEFAULT       | []              | DEFAULT
		WITH_MOJANG   | []              | DEFAULT
		WITH_SRG      | []              | DEFAULT
		DEFAULT       | ['a', 'srg']    | DEFAULT
		WITH_SRG      | ['a', 'srg']    | WITH_SRG
		WITH_MOJANG   | ['a', 'srg']    | DEFAULT
		DEFAULT       | ['mojang', 'a'] | DEFAULT
		WITH_SRG      | ['mojang', 'a'] | DEFAULT
		WITH_MOJANG   | ['mojang', 'a'] | WITH_MOJANG
	}
}
