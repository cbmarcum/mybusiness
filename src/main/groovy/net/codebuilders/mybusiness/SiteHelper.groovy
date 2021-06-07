package net.codebuilders.mybusiness

class SiteHelper {


    public static def getNoImage() {
        return Photo.findByName("no-image")?.photo?.getCloudFile("large")
    }

    public static def getThumbNoImage() {
        return Photo.findByName("no-image")?.photo?.getCloudFile("thumb")
    }
}