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

public enum ReturnCode {
    OK("OK"),
    ERROR_CN_TOO_MANY("ERROR_CN_TOO_MANY"),
    ERROR_COMM("ERROR_COMM"),
    ERROR_ROUTE("ERROR_ROUTE"),
    ERROR_TEXT("ERROR_TEXT");

    private String code;

    ReturnCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
