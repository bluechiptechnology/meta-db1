LICENSE = "MIT"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} += "bash"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://egpioconf \
           file://egpioget \
           file://egpioset \
           file://egpiomap"

FILES:${PN} += "/usr/local/bin/egpioconf \
                /usr/local/bin/egpioget \
                /usr/local/bin/egpioset \
                /usr/local/bin/egpiomap"

do_install() {
    install -d ${D}/usr/local/bin
    install -m 0755 ${WORKDIR}/egpioconf ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/egpioget ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/egpioset ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/egpiomap ${D}/usr/local/bin/
}
