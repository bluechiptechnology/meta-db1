LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit systemd

SYSTEMD_SERVICE:${PN} = "qrtr.service rmtfs.service"

SRC_URI += " file://qrtr.service \
             file://rmtfs.service \
             file://bin \
             file://lib \
             file://boot"

FILES:${PN} += "${systemd_unitdir}/system/qrtr.service \
                ${systemd_unitdir}/system/rmtfs.service \
                /usr/local/bin/qrtr-cfg \
                /usr/local/bin/qrtr-ns \
                /usr/local/bin/rmtfs \
                /usr/local/lib/libqrtr.so.1.0 \
                /usr/local/lib/libqrtr.so \
                /usr/local/lib/libqrtr.so.1 \
                /boot/modem_*"

INSANE_SKIP:${PN} += "already-stripped file-rdeps"

do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/qrtr.service ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/rmtfs.service ${D}/${systemd_unitdir}/system

    install -d ${D}/usr/local/bin
    install -m 0755 ${WORKDIR}/bin/qrtr-cfg ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/bin/qrtr-ns ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/bin/rmtfs ${D}/usr/local/bin/

    install -d ${D}/usr/local/lib
    install -m 0755 ${WORKDIR}/lib/libqrtr.so.1.0 ${D}/usr/local/lib/
    install -m 0755 ${WORKDIR}/lib/libqrtr.so ${D}/usr/local/lib/
    install -m 0755 ${WORKDIR}/lib/libqrtr.so.1 ${D}/usr/local/lib/

    install -d ${D}/boot
    install -m 0644 ${WORKDIR}/boot/modem_fs1 ${D}/boot
    install -m 0644 ${WORKDIR}/boot/modem_fs2 ${D}/boot
    install -m 0644 ${WORKDIR}/boot/modem_fsc ${D}/boot
    install -m 0644 ${WORKDIR}/boot/modem_fsg ${D}/boot
}
