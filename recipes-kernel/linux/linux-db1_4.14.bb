SUMMARY = "Kernel recipe for db1"
COMPATIBLE_MACHINE:db1 = "db1"
KERNEL_VERSION_SANITY_SKIP = "1"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://github.com/bluechiptechnology/linux-db1.git;branch=main;protocol=https\
           file://defconfig"
SRCREV = "3a26a666ec538de8ac5224817ed8f7ab4ea95090"

LINUX_VERSION ?= "4.14"
LINUX_VERSION_EXTENSION:append = "-custom"

PV = "${LINUX_VERSION}+git${SRCPV}"

KERNEL_IMAGETYPE = "Image"
