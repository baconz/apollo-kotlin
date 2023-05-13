import com.apollographql.apollo3.api.unsafeCastOrCast
import jsexport.GetAnimalQuery
import kotlin.test.Test
import kotlin.test.assertEquals


val response = """
          {
            "animal": {
              "__typename": "Cat",
              "name": "Noushka",
              "species": "Maine Coon"
            },
            "direction": "SOUTH",
            "point": {
              "x": 1,
              "y": 2
            }
          }          
        """

expect fun data(response: String): GetAnimalQuery.Data

class JsExportTest {
  @Test
  fun test() {
    val data = data(response)

    assertEquals("Maine Coon", data.animal.species, )
    assertEquals("Cat", data.animal.__typename, )
    assertEquals("Noushka", data.animal.unsafeCastOrCast<GetAnimalQuery.Data.CatAnimal>().name, )

    assertEquals("SOUTH", data.direction, )

    assertEquals(1, data.point?.x, )
    assertEquals(2, data.point?.y, )
    // NOTE: The behavior of this will be different for JS vs non-JS platforms. On JS platforms it will be non-null
    //assertNull(data.animal.unsafeCastOrCast<GetAnimalQuery.Data.OtherAnimal>(), )
  }
}
