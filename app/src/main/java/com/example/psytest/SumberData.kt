package com.example.psytest

class SumberData {
    companion object {
        fun buatSetData(): ArrayList<ListObjKategori> {
            val list = ArrayList<ListObjKategori>()

            list.add(
                ListObjKategori(
                    "Logika Aritmatika",
                "10 SOAL",
                    "https://previews.123rf.com/images/valterz/valterz1902/valterz190200002/122380592-iq-test-vector-logo-isolated-on-white-background-intellectual-quotient-iq-intelligence-human-brain.jpg"
                )

            )
            list.add(
                ListObjKategori(
                    "Penalaran logika dan aritmatika",
                "10 SOAL",
                "https://play-lh.googleusercontent.com/aUwgCYqIPWzYuDXP9fWtAbSWQgsFAYbURwNdxJuMiG7mP6ooHe2YUwyWnFCPv3cVmw"
                )
            )
            return list
}
    }
}