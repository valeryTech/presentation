package tech.valery.presentation.formatters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.service.PerformanceService;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Component
public class RoomFormatter implements Formatter<Room> {
    @Autowired
    PerformanceService performanceService;

    @Override
    public Room parse(String text, Locale locale) throws ParseException {
        List<Room> rooms = performanceService.getRooms();
        for (Room room :
                rooms) {
            if (room.getNumber() == Integer.valueOf(text))
                return room;
        }
        throw new ParseException("room not found:" + text, 0);
    }

    @Override
    public String print(Room room, Locale locale) {
        return String.valueOf(room.getNumber());
    }
}
