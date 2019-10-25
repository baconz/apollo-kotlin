// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.arguments_complex;

import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.internal.QueryDocumentMinifier;
import com.example.arguments_complex.type.Episode;
import java.io.IOException;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestQuery implements Query<TestQuery.Data, Optional<TestQuery.Data>, TestQuery.Variables> {
  public static final String OPERATION_ID = "ea0219363b8af60b029b30af551861cbae30648978be2060651eacc0e34a79d0";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "query TestQuery($episode: Episode, $stars: Int!, $greenValue: Float!) {\n"
        + "  heroWithReview(episode: $episode, review: {stars: $stars, favoriteColor: {red: 0, green: $greenValue, blue: 0}, listOfStringNonOptional: []}, listOfInts: [$stars, $stars]) {\n"
        + "    __typename\n"
        + "    name\n"
        + "    height(unit: FOOT)\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "TestQuery";
    }
  };

  private final TestQuery.Variables variables;

  public TestQuery(@NotNull Input<Episode> episode, int stars, double greenValue) {
    Utils.checkNotNull(episode, "episode == null");
    variables = new TestQuery.Variables(episode, stars, greenValue);
  }

  @Override
  public String operationId() {
    return OPERATION_ID;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public Optional<TestQuery.Data> wrapData(TestQuery.Data data) {
    return Optional.fromNullable(data);
  }

  @Override
  public TestQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<TestQuery.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  public static final class Builder {
    private Input<Episode> episode = Input.absent();

    private int stars;

    private double greenValue;

    Builder() {
    }

    public Builder episode(@Nullable Episode episode) {
      this.episode = Input.fromNullable(episode);
      return this;
    }

    public Builder stars(int stars) {
      this.stars = stars;
      return this;
    }

    public Builder greenValue(double greenValue) {
      this.greenValue = greenValue;
      return this;
    }

    public Builder episodeInput(@NotNull Input<Episode> episode) {
      this.episode = Utils.checkNotNull(episode, "episode == null");
      return this;
    }

    public TestQuery build() {
      return new TestQuery(episode, stars, greenValue);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final Input<Episode> episode;

    private final int stars;

    private final double greenValue;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(Input<Episode> episode, int stars, double greenValue) {
      this.episode = episode;
      this.stars = stars;
      this.greenValue = greenValue;
      if (episode.defined) {
        this.valueMap.put("episode", episode.value);
      }
      this.valueMap.put("stars", stars);
      this.valueMap.put("greenValue", greenValue);
    }

    public Input<Episode> episode() {
      return episode;
    }

    public int stars() {
      return stars;
    }

    public double greenValue() {
      return greenValue;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          if (episode.defined) {
            writer.writeString("episode", episode.value != null ? episode.value.rawValue() : null);
          }
          writer.writeInt("stars", stars);
          writer.writeDouble("greenValue", greenValue);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("heroWithReview", "heroWithReview", new UnmodifiableMapBuilder<String, Object>(3)
      .put("episode", new UnmodifiableMapBuilder<String, Object>(2)
        .put("kind", "Variable")
        .put("variableName", "episode")
        .build())
      .put("review", new UnmodifiableMapBuilder<String, Object>(3)
        .put("stars", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "stars")
          .build())
        .put("favoriteColor", new UnmodifiableMapBuilder<String, Object>(3)
          .put("red", "0")
          .put("green", new UnmodifiableMapBuilder<String, Object>(2)
            .put("kind", "Variable")
            .put("variableName", "greenValue")
            .build())
          .put("blue", "0.0")
          .build())
        .put("listOfStringNonOptional", "[]")
        .build())
      .put("listOfInts", "[{kind=Variable, variableName=stars}, {kind=Variable, variableName=stars}]")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<HeroWithReview> heroWithReview;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable HeroWithReview heroWithReview) {
      this.heroWithReview = Optional.fromNullable(heroWithReview);
    }

    public Optional<HeroWithReview> heroWithReview() {
      return this.heroWithReview;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], heroWithReview.isPresent() ? heroWithReview.get().marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "heroWithReview=" + heroWithReview
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return this.heroWithReview.equals(that.heroWithReview);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= heroWithReview.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final HeroWithReview.Mapper heroWithReviewFieldMapper = new HeroWithReview.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final HeroWithReview heroWithReview = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<HeroWithReview>() {
          @Override
          public HeroWithReview read(ResponseReader reader) {
            return heroWithReviewFieldMapper.map(reader);
          }
        });
        return new Data(heroWithReview);
      }
    }
  }

  public static class HeroWithReview {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forDouble("height", "height", new UnmodifiableMapBuilder<String, Object>(1)
      .put("unit", "FOOT")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String name;

    final Optional<Double> height;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public HeroWithReview(@NotNull String __typename, @NotNull String name,
        @Nullable Double height) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.name = Utils.checkNotNull(name, "name == null");
      this.height = Optional.fromNullable(height);
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * What this human calls themselves
     */
    public @NotNull String name() {
      return this.name;
    }

    /**
     * Height in the preferred unit, default is meters
     */
    public Optional<Double> height() {
      return this.height;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], name);
          writer.writeDouble($responseFields[2], height.isPresent() ? height.get() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "HeroWithReview{"
          + "__typename=" + __typename + ", "
          + "name=" + name + ", "
          + "height=" + height
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof HeroWithReview) {
        HeroWithReview that = (HeroWithReview) o;
        return this.__typename.equals(that.__typename)
         && this.name.equals(that.name)
         && this.height.equals(that.height);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        h *= 1000003;
        h ^= height.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<HeroWithReview> {
      @Override
      public HeroWithReview map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String name = reader.readString($responseFields[1]);
        final Double height = reader.readDouble($responseFields[2]);
        return new HeroWithReview(__typename, name, height);
      }
    }
  }
}
