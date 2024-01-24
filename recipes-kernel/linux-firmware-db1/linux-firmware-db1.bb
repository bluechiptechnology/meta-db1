LICENSE = "CLOSED"

S = "${WORKDIR}"

COMPATIBLE_MACHINE = "db1"

SRC_URI:append = "file://qcom \
                  file://modem \
                  file://wcnss \
                  file://wlan"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware

    ln -s qcom/a300_pfp.fw ${D}${nonarch_base_libdir}/firmware/a300_pfp.fw
    ln -s qcom/a300_pm4.fw ${D}${nonarch_base_libdir}/firmware/a300_pm4.fw

    cp -R ${S}/qcom/ ${D}${nonarch_base_libdir}/firmware/
    cp -R ${S}/wlan/ ${D}${nonarch_base_libdir}/firmware/
    cp -R ${S}/modem/* ${D}${nonarch_base_libdir}/firmware/
    cp -R ${S}/wcnss/* ${D}${nonarch_base_libdir}/firmware/
}

INSANE_SKIP:${PN}-mixed = "arch" 
FILES:${PN}-mixed = "${nonarch_base_libdir}/firmware/"

PACKAGES = "${PN}-mixed"
