package jp.prototype.batch.util;

import java.util.ArrayList;
import java.util.List;

import jp.prototype.common.model.Item;

public class ItemFactory {

  public static List<Item> create(int startId, int endId, String type) {

    List<Item> items = new ArrayList<>(endId);
    for (int i = startId; i <= endId; i++) {
      items.add(new Item(i, "item" + i, type));
    }
    return items;
  }

}
