package org.students.homework2.services;

import java.util.List;

// расширить на несколько способов обработки данных (из бд и т.д.)
public interface DataLoader<T> {
    List<T> load(String path);
}