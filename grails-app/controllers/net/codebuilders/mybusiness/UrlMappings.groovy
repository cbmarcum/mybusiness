package net.codebuilders.mybusiness

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'home', action: 'index') // was view:"/index"
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
