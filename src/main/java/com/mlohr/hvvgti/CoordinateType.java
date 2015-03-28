package com.mlohr.hvvgti;

/*
HVVGTI Java API
Copyright (C) 2015  Matthias Lohr <matthias@lohr.me>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License version 3 as
published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

public enum CoordinateType {
    EPSG_4326("EPSG_4326"),
    EPSG_31467("EPSG_31467");

    private String typeString;

    CoordinateType(String typeString) {
        this.typeString = typeString;
    }

    public static CoordinateType getDefault() {
        return EPSG_4326;
    }

    @Override
    public String toString() {
        return typeString;
    }
}
