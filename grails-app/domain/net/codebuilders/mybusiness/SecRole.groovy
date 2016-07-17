package net.codebuilders.mybusiness

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class SecRole implements Serializable {

	private static final long serialVersionUID = 1

	String authority

	static constraints = {
		authority blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
