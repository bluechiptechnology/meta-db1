inherit python3native

LICENSE = "GPLv2"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
RPROVIDES:${PN} = "qtestsign"
DEPENDS += "python3-cryptography"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/msm8916-mainline/qtestsign.git;branch=main;protocol=https"
SRCREV = "9ed0787b76b911b346b6dcd0b94093fcf53ff91f"

do_install () {
    install -d ${BASE_WORKDIR}/fw
    install -D ${S}/fw/* ${BASE_WORKDIR}/fw
    install -m 0755 ${S}/qtestsign.py ${BASE_WORKDIR}/qtestsign.py
}
