package com.app.projectgroup3.utils

import com.app.projectgroup3.model.Place

fun getPlaces() : List<Place>{
    val places = arrayListOf<Place>()
    val place1 = Place("1",
        "Plaza de Barranco",
        "Plaza de Barranco",
        "La plaza de Barranco es un centro colorido de la actividad. Las atracciones allí incluyen la biblioteca Municipal, que también alberga la oficina de turismo y el parque en sí, que es un bonito lugar para relajarse y encontrarse con otras personas.",
        "https://media-cdn.tripadvisor.com/media/photo-s/12/36/63/10/alrededores.jpg",
        "Barranco, Lima, Peru",
        -12.148796370884893,
        -77.02128414558528
    )
    val place2 = Place("2",
        "Plaza Mayor de Lima",
        "Plaza Mayor de Lima",
        "La Plaza Mayor de Lima o Plaza de Armas de Lima, es el sitio fundacional de la ciudad de Lima, capital del Perú. Es el principal espacio público de la ciudad",
        "https://www.museosdelima.com/wp-content/uploads/2018/01/plaza-mayor-lima-3.jpg",
        "Cercado de Lima, Lima, Peru",
        -12.04576246177476,
        -77.03049215838624
    )
    val place3 = Place("3",
        "Parque de las Leyendas",
        "Parque de las Leyendas",
        "El Parque de las Leyendas \"Felipe Benavides Barreda\" es un zoológico ubicado en el Distrito de San Miguel en la ciudad de Lima en Perú. Es administrado por la Municipalidad Metropolitana de Lima.",
        "https://cde.canaln.pe/actualidad-parque-leyendas-inaugurara-este-mes-autocine-lima-park-n425163-764x480-920166.jpg",
        "San Miguel, Lima, Peru",
        -12.06946902474063,
        -77.08800173140573
    )
    val place4 = Place("4",
        "Parque de la Reserva",
        "Parque de la Reserva",
        "El Parque de la Reserva es un espacio público ajardinado ubicado en el barrio de Santa Beatriz en el distrito de Lima, en la capital del Perú. De configuración irregular, se ubica entre dos de las principales calles de la ciudad, la vía rápida Paseo de la República y la Avenida Arequipa",
        "https://tvperu.gob.pe/sites/default/files/agua_2_0.jpg",
        "Cercado de Lima, Lima, Peru",
        -12.070696200962104,
        -77.03403390256913
    )
    val place5 = Place("5",
        "Parque Central de Miraflores",
        "Parque Central de Miraflores",
        "El Parque Central de Miraflores, popularmente conocido como el «Parque Kennedy» o el Parque de Miraflores, es uno de los parques de Lima más populares y visitados por su ubicación tan céntrica en el distrito de Miraflores. Está conformado por 2 parques: Parque 7 de Junio y Parque Kennedy, uno al lado del otro.",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Lima_CentralPark_7_June.JPG/420px-Lima_CentralPark_7_June.JPG",
        "Miraflores, Lima, Peru",
        -12.121471240360407,
        -77.03030928722214
    )
    val place6 = Place("6",
        "Malecón de la Reserva",
        "Malecón de la Reserva",
        "El malecón de la Reserva es un malecón o paseo urbano que recorre los acantilados del distrito de Miraflores, en Lima, Perú. Es el tramo sur del malecón de Miraflores que comprende la vía peatonal que va desde el puente Villena hasta la bajada Armendaríz. Fue diseñado por el arquitecto peruano Augusto Benavides Diez Canseco.",
        "https://hotel-elviajero.com/wp-content/uploads/2018/04/m2.jpg",
        "Miraflores, Lima, Peru",
        -12.121471240360407,
        -77.03030928722214
    )
    places.add(place1)
    places.add(place2)
    places.add(place3)
    places.add(place4)
    places.add(place5)
    places.add(place6)
    return places
}