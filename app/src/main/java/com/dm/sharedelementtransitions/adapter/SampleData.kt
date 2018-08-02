package com.dm.sharedelementtransitions.adapter

import android.os.Parcel
import android.os.Parcelable

data class Place(val id: Long, val name: String, val description: String, val image: String) :
    Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(description)
        writeString(image)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Place> = object : Parcelable.Creator<Place> {
            override fun createFromParcel(source: Parcel): Place =
                Place(source)
            override fun newArray(size: Int): Array<Place?> = arrayOfNulls(size)
        }
    }
}

fun getSampleDataList() = listOf(
    Place(
        10000,
        "Paris",
        "Paris is the pinnacle of art, history, culture and tourism. The City of Light is home to the expansive Louvre art museum, the magical Eiffel Tower and the charming Champs-Élysées lined with high-end stores and boutiques. Quaint cafes and patisseries rub elbows with both casual eateries and Michelin-starred restaurants in a city where the food scene is second to none. What's more, Paris plays host to dozens of fun festivals throughout the year. (GETTY IMAGES)",
        "https://www.usnews.com/dims4/USNEWS/827ce5d/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2Fbf%2F6f%2F42b527c34bd1961a0549738ad027%2F31.%20-1.%20Paris-Getty.jpg"
    ),
    Place(
        10001,
        "Rome",
        "Delicious food (pizza, pasta, gelato, you name it), rich coffee and excellent wine are a few reasons why travelers love Rome. However, food and drink aren't the only things this city has to offer. You'll find major highlights of art and history, from the Galleria Borghese to the Colosseum. Meanwhile, nearby city-state Vatican City is home to St. Peter's Basilica and the Sistine Chapel. Rome is also easy to reach from other destinations within Europe, and there are direct flights here from many other parts of the world (including the U.S.).",
        "https://www.usnews.com/dims4/USNEWS/22b9bd9/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F4b%2Fb5%2F987d142c4c8ca103177ab7d057a1%2F29-3.%20Rome-Getty.jpg"
    ),
    Place(
        10002,
        "South Island, New Zealand",
        "It's the breathtaking natural landscape that woos adventurers to New Zealand's South Island, where outdoor opportunities abound. Visitors can explore the area's glaciers via helicopter, watch for wildlife like whales and penguins, tour the region's wineries and stargaze on top of Mount Cook. The island's many lakes and parks also invite travelers to kayak, sail, hike and bike to soak up every bit of New Zealand nature.",
        "https://www.usnews.com/dims4/USNEWS/46239f4/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2Ffb%2Fef%2Fc12360464b8b95b40037301de130%2F26.%206-South%20Island-Getty.jpg"
    ),
    Place(
        10003,
        "Yosemite National Park, California",
        "Stretching across nearly 1,200 square miles of nature, Yosemite National Park is every outdoor enthusiast's paradise. Travelers to this national park will encounter towering waterfalls, millennia-old Sequoia trees, striking cliff faces and some of the most interesting rock formations in the United States. Hiking is the best way to enjoy a trip to Yosemite, but visitors can also go swimming in some of the park's rivers, bike the various trails and enjoy photography walks or nature programs.",
        "https://www.usnews.com/dims4/USNEWS/cac4f60/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F1d%2F05%2F58e52f0f4a07b59cd3dbe50ccf02%2F19.-%2013.%20Yosemite-Getty.jpg"
    ),
    Place(
        10004,
        "Maui, Hawaii",
        "With captivating beaches, several scenic golf courses and even a volcano, Maui is almost an otherworldly vacation destination. Beach bums will have plenty of sandy shoreline to choose from: Waianapanapa features jet-black sand and is framed by an emerald green jungle, Kaanapali Beach is great for snorkeling and Wailea Beach is popular with those just looking to relax and take in the views. Meanwhile, a drive along the Road to Hana is another excellent way to see all the beauty this Hawaiian island has to offer.",
        "https://www.usnews.com/dims4/USNEWS/864c14e/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F41%2F2a%2Fa1c204064ee6afd21cb67ae332e3%2F17.-%2015.%20Maui-Getty.jpg"
    ),
    Place(
        10005,
        "Sydney",
        "The largest city in Australia offers a wealth of activities and attractions for travelers to enjoy. You can climb the Sydney Harbour Bridge for spectacular views (and a heart-pumping adventure), stroll through Darling Harbour and peruse the 74-acre Royal Botanic Garden. When it's time to wind down, head to Coogee Beach to sunbathe or snorkel, or to Bondi Beach to watch the surfers shred waves.",
        "https://www.usnews.com/dims4/USNEWS/70bd38d/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F21%2F76%2Fba001ccd40aa8533e34f56156648%2F16.%20-16.%20Sydney-Getty.jpg"
    ),
    Place(
        10006,
        "Hong Kong",
        "Hong Kong is a destination that puts travelers' senses into overdrive. The glittering skyline, the dynamic food scene, the historical sites and the family-friendly attractions are just a few reasons visitors can't get enough of this bustling metropolis. Start your trip with a tour of the waterfront on the Star Ferry, and pencil in visits to Hong Kong Disneyland or Ocean Park amusement park if you're traveling with kids. Meanwhile, foodies will delight in the culinary options: Dim sum, stir-fry and pineapple buns should be a few things you sample.",
        "https://www.usnews.com/dims4/USNEWS/8395109/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F8c%2Fa1%2F454d4f264773b543c6d2ee017a4b%2F15.-17.%20Hong%20Kong-Getty.jpg"
    ),
    Place(
        10007,
        "Bali, Indonesia",
        "This Indonesian island provides the perfect mix of opportunities for cultural exploration and relaxation. Travelers should spend time visiting and learning about the island's many ancient temples before heading to one of its beautiful beaches. As far as accommodations go, Bali features dozens of villas, hotels and resorts (at varying price points). To up the luxury factor, stay at a five-star resort like the Four Seasons or St. Regis and indulge in a spa treatment. ",
        "https://www.usnews.com/dims4/USNEWS/002afbe/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2Ff1%2F3e%2Ffa0dbf6a48d2be396522587c06da%2F9-23.%20Bali%2C%20Indonesia-Getty.jpg"
    ),
    Place(
        10008,
        "Great Barrier Reef",
        "Though the Great Barrier Reef's coral population is diminishing, it's still one of the most awe-inspiring destinations in the world. The region is home to more than 3,000 individual reefs and coral cays, and colorful fish like angelfish, clownfish, parrotfish and butterfly fish, in addition to various species of sharks. When you're not exploring underwater, you can spend time relaxing on Whitehaven Beach or sailing on the turquoise Coral Sea.",
        "https://www.usnews.com/dims4/USNEWS/0314671/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F71%2Fd5%2F6bddc81a4df2abb0fce91784b8c6%2F7.%2025-Great%20Barrier%20Reef-Getty.jpg"
    ),
    Place(
        10009,
        "Rio de Janeiro",
        "The Cidade Maravilhosa (Marvelous City) honors its moniker by welcoming visitors with its scenic vistas and lively festivals. The extravagant Carnival celebration held each February or March includes days of masquerade balls, parades and parties. Those looking for a well-rounded trip to Rio should check out the towering Christ the Redeemer statue, explore the 340-acre Jardim Botânico, and spend some time lazing on Ipanema or Copacabana beach.",
        "https://www.usnews.com/dims4/USNEWS/5cb64c9/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F19%2Fb7%2F03eed5454fb084ab1871b0709a18%2F13.%2019-Rio-Getty.jpg"
    ),
    Place(
        10010,
        "Santorini, Greece",
        "See a sunset in Santorini and you'll know why this Greek destination is one of the world's best places to visit. In addition to offering an exquisite evening atmosphere, Santorini boasts colorful black and red sand beaches, ancient ruins and several wineries. But the picture-perfect scenery is what draws most travelers: You'll see cliffside whitewashed buildings that line the brilliantly blue caldera. What's more, the luxury hotels here are some of the world's best.",
        "https://www.usnews.com/dims4/USNEWS/c87edfc/2147483647/resize/1200x%3E/quality/85/?url=http%3A%2F%2Fmedia.beam.usnews.com%2F3b%2F5b%2Fccffb4184e18bc88025c9e671621%2F18-14.%20Santorini-Getty.jpg"
    )
)