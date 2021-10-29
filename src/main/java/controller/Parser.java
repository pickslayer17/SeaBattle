package controller;

import java.io.File;

public interface Parser <T>{
    String parseTo(String data);
    String parseFrom(String data);
}
