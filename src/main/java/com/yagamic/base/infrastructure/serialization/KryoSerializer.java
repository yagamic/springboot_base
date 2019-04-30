package com.yagamic.base.infrastructure.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import org.apache.commons.codec.binary.Base64;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiemeilong on 15-8-20.
 */
public abstract class KryoSerializer {

//    protected ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
//        protected Kryo initialValue() {
//            return kryo();
//        }
//    };
//
//    abstract protected int getKryoId();
//
//    private Kryo kryo() {
//        Kryo kryo = new Kryo();
//        kryo.register(getValueClass(), getKryoId());
//        return kryo;
//    }

//    protected byte[] serializeObject(T object) {
//        Output out = new Output(new ByteArrayOutputStream());
//        kryos.get().writeObject(out, object);
//        byte[] res = out.toBytes();
//        out.close();
//        return res;
//    }
//
//    protected <K> byte[] serializeDefaultType(K object) {
//        Output out = new Output(new ByteArrayOutputStream());
//        kryos.get().writeObject(out, object);
//        byte[] res = out.toBytes();
//        out.close();
//        return res;
//    }
//
//    protected T deserializeObject(byte[] data) {
//        return kryos.get().readObject(new Input(new ByteArrayInputStream(data)), getValueClass());
//    }
//
//    protected <K> K deserializeDefaultType(byte[] data, Class<K> type) {
//        return kryos.get().readObject(new Input(new ByteArrayInputStream(data)), type);
//    }

    protected <T extends Serializable> String serializationObject(Object obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(new Base64().encode(b));
    }

    @SuppressWarnings("unchecked")
    protected <T extends Serializable> T deserializationObject(String obj,
                                                               Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());

        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }

    protected <T extends Serializable> String serializationList(List<T> obj,
                                                                Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(new Base64().encode(b));
    }

    @SuppressWarnings("unchecked")
    protected <T extends Serializable> List<T> deserializationList(String obj,
                                                                   Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);

        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }

//    abstract protected Class<T> getValueClass();
}
