rootProject.name = "dvoretckii"
include("lab-2")
include("lab-2:DAO")
findProject(":lab-2:DAO")?.name = "DAO"
include("lab-2:Service")
findProject(":lab-2:Service")?.name = "Service"
include("lab-2:Controller")
findProject(":lab-2:Controller")?.name = "Controller"
include("lab2")
include("lab-4")
include("lab-4:Controller")
findProject(":lab-4:Controller")?.name = "Controller"
include("lab-4:DAO")
findProject(":lab-4:DAO")?.name = "DAO"
include("lab-4:Service")
findProject(":lab-4:Service")?.name = "Service"
