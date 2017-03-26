package net.codebuilders.mybusiness

import com.sun.syndication.feed.module.itunes.EntryInformationImpl
import com.sun.syndication.feed.module.itunes.types.Duration

class EnhancediTunesEntryInformationImpl extends EntryInformationImpl {

    void setDurationText(String s) {
        setDuration new Duration(s)
    }
}
