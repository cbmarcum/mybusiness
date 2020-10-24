package net.codebuilders.mybusiness

import com.rometools.modules.itunes.EntryInformationImpl
import com.rometools.modules.itunes.types.Duration

class EnhancediTunesEntryInformationImpl extends EntryInformationImpl {

    void setDurationText(String s) {
        setDuration new Duration(s)
    }
}
