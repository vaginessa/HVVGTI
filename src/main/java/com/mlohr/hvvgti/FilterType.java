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

public enum FilterType {
    NO_FILTER("NO_FILTER"),
    HVV_LISTED("HVV_LISTED");

    String type;

    FilterType(String type) {
        this.type = type;
    }

    public static FilterType getDefault() {
        return HVV_LISTED;
    }

    @Override
    public String toString() {
        return type;
    }
}
