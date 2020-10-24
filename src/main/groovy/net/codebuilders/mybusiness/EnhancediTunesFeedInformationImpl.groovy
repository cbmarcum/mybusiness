package net.codebuilders.mybusiness

import com.rometools.modules.itunes.FeedInformationImpl
import com.rometools.modules.itunes.types.Category
import com.rometools.modules.itunes.types.Subcategory

class EnhancediTunesFeedInformationImpl extends FeedInformationImpl {

    void setCategories(List cats) {
        def newcats = []
        for (entry in cats) {
            def c
            if (entry instanceof List) {
                c = new Category(entry[0])
                if (entry.size() > 1) {
                    c.subcategory = new Subcategory(entry[1])
                }
            } else if (entry instanceof String) {
                c = new Category(entry)
            } else if (entry instanceof Category) {
                c = entry
            }
            newcats << c
        }
        super.setCategories newcats
    }
}
